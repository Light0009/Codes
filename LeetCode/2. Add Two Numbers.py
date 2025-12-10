# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        s1 = ""
        s2 = ""
        while l1 != None:
            s1 = str(l1.val) + s1
            l1 = l1.next
        while l2 != None:
            s2 = str(l2.val) + s2
            l2 = l2.next
        total = int(s1) + int(s2)
        stotal = str(total)
        store = []
        store = ListNode(int(stotal[-1]))
        ans = store
        for i in range(len(stotal)-2, -1, -1):
            store.next = ListNode(int(stotal[i]))
            store = store.next
        return ans
