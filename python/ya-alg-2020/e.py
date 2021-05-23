from time import time
def E():
    from collections import defaultdict

    def longest_branch(graph, start, visited=None):
        # t = time()
        if visited is None:
            visited = set()
        queue = [(start, 1), ]
        branch = []
        res = []
        while queue:
            root, lvl = queue.pop()
            queue.extend((n, lvl+1) for n in graph[root] if n not in visited)

            if len(branch) >= lvl:
                if len(branch) > len(res):
                    res = branch.copy()
                while len(branch) >= lvl:
                    visited.remove(branch.pop())

            branch.append(root)
            visited.add(root)

        # print(time() - t)
        if len(branch) > len(res):
            return branch
        else:
            return res

    with open('input.txt', 'r') as f_in, open('output.txt', 'w') as f_out:
        n = int(f_in.readline())
        if n <= 3:
            f_out.write('1 2')
            return

        net = defaultdict(list)  # для каждого узла множество смежных
        # связный граф с n узлами и n-1 ребрами - дерево
        for _ in range(n-1):
            a, b = f_in.readline().split()
            net[a].append(b), net[b].append(a)
        # у дерева всегда есть самый длинный путь, который можно найти используя два обхода
        longest_path = longest_branch(net, start='1')
        longest_path = longest_branch(net, start=longest_path[-1])
        # если бы требовалось найти один такой узел, то нужно было брать центр самого длинного пути
        # с двумя узлами: проделеаем это дважды
        n = len(longest_path) - 1
        split = n // 2
        # левая часть и центр
        lp = longest_branch(net, start=longest_path[0], visited={longest_path[n-split+1], })
        split_left = lp[(len(lp) - 1) // 2]
        # правая часть и центр
        lp = longest_branch(net, start=longest_path[-1], visited={longest_path[split-1], })
        split_right = lp[(len(lp) - 1) // 2]
        if split_left == split_right:
            # в случае симмитричного графа у нас может выбраться центральный узел дважды
            split_right = lp[(len(lp) - 1) // 2 + 1]

        f_out.write(f'{split_left} {split_right}')
E()
