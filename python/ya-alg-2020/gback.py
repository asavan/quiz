import requests
def F():

    with open('input.txt', 'r') as f_in, open('output.txt', 'w') as f_out:
        url = f_in.readline().strip() + ':' + f_in.readline().strip()
        a, b = f_in.readline().strip(), f_in.readline().strip()

        res = requests.get(url, params={'a': a, 'b': b})
        arr = map(int, res.json())
        f_out.write(f'{sum(arr)}')
F()