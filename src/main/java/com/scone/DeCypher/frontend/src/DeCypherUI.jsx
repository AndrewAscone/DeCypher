import { useState } from "react";
import "./DeCypherStyling.css";

export default function DeCypherUI() {
    const [message, setMessage] = useState("");
    const [key, setKey] = useState("");
    const [cipher, setCipher] = useState("caesar");
    const [result, setResult] = useState("");

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
    // If something goes wrong in testing, check these handle functions
    // Double check URLs being used (need to manage CORS stuff in back end)
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
            <h2>Text Encryption</h2>
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