def merge_the_tools(string, k):
    array = []
    for i in range(0, len(string), k):
        sub = string[i : i + k]
        out = ''
        for j in range(0, k):
            if sub[j] not in out:
                out += sub[j]
        print(out)
if __name__ == '__main__':
    string, k = input(), int(input())
    merge_the_tools(string, k)
