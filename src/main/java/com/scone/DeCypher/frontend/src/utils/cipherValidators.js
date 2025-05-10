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