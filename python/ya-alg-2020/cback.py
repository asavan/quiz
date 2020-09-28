def C():
    with open('input.txt', 'r') as f_in, open('output.txt', 'w') as f_out:
        k, n = map(int, f_in.readline().split())
        cards = map(int, f_in.readline().split())

        V, P = 0, 0
        for card in cards:
            mod3, mod5 = card % 3 == 0, card % 5 == 0
            if mod3 == mod5:
                continue
            elif mod3:
                P += 1
            else:
                V += 1

            if V == k:
                res = 'Vasya'
                break
            elif P == k:
                res = 'Petya'
                break
        else:
            if V > P:
                res = 'Vasya'
            elif P > V:
                res = 'Petya'
            else:
                res = 'Draw'

        f_out.write(res)
C()
