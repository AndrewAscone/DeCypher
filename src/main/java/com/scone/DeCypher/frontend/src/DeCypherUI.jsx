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
            
        </div>
    );
}