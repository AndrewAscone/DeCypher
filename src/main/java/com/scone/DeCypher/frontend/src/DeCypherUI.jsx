import { useState } from "react";
import "./DeCypherStyling.css";
import * as cipherValidators from './utils/cipherValidators';

export default function DeCypherUI() {
    // === State Variables ===
    const [message, setMessage] = useState("");
    const [key, setKey] = useState("");
    const [cipher, setCipher] = useState("caesar");
    const [result, setResult] = useState("");
    const [mode, setMode] = useState("text"); // "text" or "file"
    const [file, setFile] = useState(null);

    // === Configuration ===
    const apiUrl = import.meta.env.VITE_API_BASE_URL;

    const isKeyValid = () => {
        return cipherValidators.validateKeyForCipher(cipher, key);
    };

    const getCipherInfo = (cipher) => {
        switch (cipher) {
            case "caesar":
                return "Caesar Cipher: This cipher uses a numeric key to shift the letters in the message.\nKey: A whole number greater than zero.";
            case "vigenere":
                return "Vigenere Cipher: This cipher uses a word or phrase to shift letters based on each character.\nKey: A string of alphabetic characters.";
            case "atbash":
                return "Atbash Cipher: This cipher uses a fixed substitution, so it does not need a key.\nKey: Leave this field empty";
            case "aes":
                return "AES Cipher: This cipher uses a secret key for secure encryption.\nKey: Must be exactly 16 characters";
        }
    };

    const handleTextEncrypt = async () => {
        if (!isKeyValid()) {
            setResult("Invalid key for selected cipher.");
            return;
        }

        try {
            const response = await fetch(`${apiUrl}/api/encrypt`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ text: message, key: key, cipher: cipher})
            });

            if(!response.ok){
                throw new Error("Server responded with an error.");
            }

            const data = await response.json();
            setResult(data.result);
        } catch (error) {
            console.error("Encryption failed:", error);
            setResult("Error: Encryption failed.");
        }
    };
   
    const handleTextDecrypt = async () => {
        if (!isKeyValid()) {
            setResult("Invalid key for selected cipher.");
            return;
        }
        
        try {
            const response = await fetch(`${apiUrl}/api/decrypt`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ text: message, key: key, cipher: cipher}),
            });

            if(!response.ok) {
                throw new Error("Server responded with an error.");
            }

            const data = await response.json();
            setResult(data.result);
        } catch (error) {
            console.error("Decryption failed:", error);
            setResult("Error: Decryption failed.");
        }
    };

    const handleFileEncrypt = async () => {
        if (!isKeyValid()) {
            setResult("Invalid key for selected cipher.");
            return;
        }
        
        if(!file) {
            setResult("Please select a file to encrypt");
            return;
        }

        const formData = new FormData();
        formData.append("file", file);
        formData.append("key", key);
        formData.append("cipher", cipher);

        try {
            console.log("Encrypting to:", `${apiUrl}/file/encrypt`);
            const response = await fetch(`${apiUrl}/file/encrypt`, {
                method: "POST",
                body: formData,
            });

            if(!response.ok){
                throw new Error("Server responded with an error.");
            }

            const blob = await response.blob();
            const downloadUrl = window.URL.createObjectURL(blob);
            const link = document.createElement("a");
            link.href = downloadUrl;
            link.download = `encrypted_${file.name}`;
            link.click();
            window.URL.revokeObjectURL(downloadUrl);

            setResult("File encrypted and download started.");
        } catch (error) {
            console.error("File encryption failed:", error);
            setResult("Error: File encryption failed.");
        }
    };

    const handleFileDecrypt = async () => {
        if (!isKeyValid()) {
            setResult("Invalid key for selected cipher.");
            return;
        }
        
        if(!file) {
            setResult("Please select a file first.");
            return;
        }

        const formData = new FormData();
        formData.append("file", file);
        formData.append("key", key);
        formData.append("cipher", cipher);

        try {
            const response = await fetch(`${apiUrl}/file/decrypt`, {
                method: "POST",
                body: formData,
            });

            if(!response.ok){
                throw new Error("Server responded with an error.");
            }

            const blob = await response.blob();
            const downloadUrl = window.URL.createObjectURL(blob);
            const link = document.createElement("a");
            link.href = downloadUrl;
            link.download = `decrypted_${file.name}`;
            link.click();
            window.URL.revokeObjectURL(downloadUrl);

            setResult("File decrypted and download started.");
        } catch (error) {
            console.error("File decryption failed:", error);
            setResult("Error: File decryption failed.");
        }
    };

    return (
        <div className="container">
            <div className="mode-toggle">
                <button
                    className={mode === "text" ? "active" : ""}
                    onClick={() => setMode("text")}
                >
                    Text Mode
                </button>
                <button
                    className={mode === "file" ? "active" : ""}
                    onClick={() => setMode("file")}
                >
                    File Mode
                </button>
            </div>
            <h2>{mode === "text" ? "Text Encryption" : "File Encryption"}</h2>
            {mode === "text" ? (
                <>
                    <textarea
                        className="input-area"
                        placeholder="Enter your message..."
                        value={message}
                        onChange={(e) => setMessage(e.target.value)}
                    />
                    <input
                        className="input-field"
                        type="text"
                        placeholder="Enter key"
                        value={key}
                        onChange={(e) => setKey(e.target.value)}
                    />
                    {key && !isKeyValid() && (
                        <div className="warning-text">Warning: Invalid key for {cipher.toUpperCase()} cipher</div>
                    )}
                </>
            ) : (
                <>
                    <input
                        className="input-field"
                        type="file"
                        onChange={(e) => {
                            const selectedFile = e.target.files[0];
                            if (selectedFile && selectedFile.size > 100 * 1024 * 1024) {
                                alert("file size exceeds 100MB limit.")
                                e.target.value = null;
                            } else {
                                setFile(selectedFile);
                            }
                        }}
                    />
                    <input
                        className="input-field"
                        type="text"
                        placeholder="Enter key"
                        value={key}
                        onChange={(e) => setKey(e.target.value)}
                    />
                    {key && !isKeyValid() && (
                        <div className="warning-text">Warning: Invalid key for {cipher.toUpperCase()} cipher</div>
                    )}
                </>
            )}
            
            <select
                className="dropdown"
                value={cipher}
                onChange={(e) => setCipher(e.target.value)}
            >
                <option value="caesar">Caesar Cipher</option>
                <option value="vigenere">Vigenere Cipher</option>
                <option value="atbash">Atbash Cipher</option>
                <option value="aes">AES</option>
            </select>
            <div className="cipher-info">
                {getCipherInfo(cipher).split("\n").map((line, index) => (
                    <p key={index}>{line}</p>
                ))}
            </div>
            <div className="button-container">
                <button 
                    className="encrypt-btn" 
                    onClick={mode === "file" ? handleFileEncrypt : handleTextEncrypt}
                >
                    Encrypt
                </button>
                <button 
                    className="decrypt-btn" 
                    onClick={mode === "file" ? handleFileDecrypt : handleTextDecrypt}
                >
                    Decrypt
                </button>
            </div>
            {result && (
                <div className="result-box">
                    <strong>Result:</strong>
                    <p>{result}</p>
                </div>
            )}
        </div>
    );
}