a = set(map(int, input().split()))

stat = True
for i in range(int(input())):
    b = set(map(int, input().split()))
    if not b.issubset(a):
        stat = False
        break

print(stat)
