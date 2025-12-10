class Solution:
    def isValid(self, s: str) -> bool:
        left_stack = []
        left = {"(" : 0 , "{" : 1, "[" : 2}
        right = {")" : 0, "}" : 1, "]" : 2}
        pair = {")" : "(", "}" : "{", "]" : "["}
        for i in range(len(s)):
            if left.get(s[i]) == None:
                lp = pair.get(s[i])
                if len(left_stack) == 0:
                    return False
                elif left_stack[-1] == lp:
                    left_stack.pop()
                    continue
                else:
                    return False
            else:
                left_stack.append(s[i])
        if len(left_stack) == 0:
            return True
        else:
            return False
            
