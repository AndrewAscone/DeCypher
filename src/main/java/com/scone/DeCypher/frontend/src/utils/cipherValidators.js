// Caesar: key must be a non-negative integer
export function isValidCaesarKey(key) {
    const num = Number(key);
    return Number.isInteger(num) && num >= 0;
}