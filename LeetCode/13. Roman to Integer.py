class Solution:
    def romanToInt(self, s: str) -> int:
        roman = {"I" : 1, "V" : 5, "X" : 10, "L" : 50, "C" : 100, "D" : 500, "M" : 1000}
        base = 0
        for i in range(0, len(s) - 1):
            if roman.get(s[i]) < roman.get(s[i + 1]):
                base = base - roman.get(s[i])
            else:
                base = base + roman.get(s[i])
        return base + roman.get(s[len(s)-1])
