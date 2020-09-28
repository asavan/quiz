def D():
    n = int(input())
    lb = 1
    ub = n
    while True:
        curr = lb + (ub - lb) // 2
        print(curr, flush=True)
        res = input().strip()
        if res == '1':
            lb = curr + 1
        elif res == '0':
            ub = curr
        else:
            raise ValueError()
        if ub == lb:
            break
    print(f'! {ub}', flush=True)
D()
