import { useState } from "react";
import "./DeCypherStyling.css";

export default function DeCypherUI() {
    const [message, setMessage] = useState("");
    const [key, setKey] = useState("");
    const [cipher, setCipher] = useState("caesar");
    const [result, setResult] = useState("");
    const [mode, setMode] = useState("text"); // "text" or "file"
    const [file, setFile] = useState(null);

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

    const handleEncrypt = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/encrypt", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ text: message, key: key, cipher: cipher})
            });

            const data = await response.json();
            setResult(data.result);
        } catch (error) {
            console.error("Encryption failed:", error);
            setResult("Error: Encryption failed.");
        }
    };
   
    // These are only for text encryptions, need to differentiate for files
    const handleDecrypt = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/decrypt", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ text: message, key: key, cipher: cipher}),
            });

            const data = await response.json();
            setResult(data.result);
        } catch (error) {
            console.error("Decryption failed:", error);
            setResult("Error: Decryption failed.");
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
                <button className="encrypt-btn" onClick={handleEncrypt}>
                    Encrypt
                </button>
                <button className="decrypt-btn" onClick={handleDecrypt}>
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