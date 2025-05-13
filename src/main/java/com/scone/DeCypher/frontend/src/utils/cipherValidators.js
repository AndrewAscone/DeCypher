// Caesar: key must be a non-negative integer
export function isValidCaesarKey(key) {
    const num = Number(key);
    return Number.isInteger(num) && num >= 0;
}

// Vigenère: key must be a non-empty string of letters only
export function isValidVigenereKey(key) {
    return typeof key === 'string' && /^[A-Za-z]+$/.test(key);
}

// Atbash: no key required, always valid
export function isValidAtbashKey() {
    return true;
}

// AES: key must be exactly 16 bytes
export function isValidAESKey(key) {
    return typeof key === 'string' && new TextEncoder().encode(key).length === 16;
}