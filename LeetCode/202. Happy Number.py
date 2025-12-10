class Solution:
    def isHappy(self, n: int) -> bool:
        digits = [int(digit) for digit in str(n)]
        base = []
        added = n
        while sum(digits) != 1:
            base.append(added)
            digits = [x ** 2 for x in digits]
            added = sum(digits)
            digits = [int(digit) for digit in str(added)]
            if sum(digits) in base:
                return False
        return True
