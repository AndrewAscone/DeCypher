import { useState } from "react";
import "./DeCypherStyling.css";

export default function DeCypherUI() {
    const [message, setMessage] = useState("");
    const [key, setKey] = useState("");
    const [cipher, setCipher] = useState("caesar");
    const [result, setResult] = useState("");

    const handleEncrypt = () => {
        // Encryption logic will go here
        setResult(`Encrypted(${cipher}): ${message}`);
    };

    const handleDecrypt = () => {
        // Decryption logic will go here
        setResult(`Decrypted(${cipher}): ${message}`);
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