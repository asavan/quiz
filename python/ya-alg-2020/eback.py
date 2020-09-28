from collections import defaultdict
def E():
    def cluster(graph, root):
        """Наодит узлы кластера, которому принадлежит root"""
        visited = set()
        queue = [root, ]
        while queue:
            root = queue.pop()
            queue.extend(n for n in graph[root] if n not in visited)
            visited.add(root)

        return visited

    with open('input.txt', 'r') as f_in, open('output.txt', 'w') as f_out:
        n = int(f_in.readline())

        net = defaultdict(set)  # для каждого узла множество смежных
        for _ in range(n):
            a, b = f_in.readline().split()
            net[a].add(b), net[b].add(a)

        # разобъем сеть на кластеры
        clusters = dict()
        for n, subnet in net.items():
            if n not in clusters:
                c = cluster(net, n)
                for subling in c:
                    clusters[subling] = c

        q = int(f_in.readline())
        for _ in range(q):
            target, _ = f_in.readline().split()
            sources = f_in.readline().split()

            c = clusters[target]
            # Серверы следует выводить в том порядке, в котором они перечислены
            # в описании соответствующего запроса во входных данных.
            res = [n for n in sources if n in c]
            if res:
                f_out.write(f'{len(res)} {" ".join(res)}\n')
            else:
                f_out.write('0\n')
E()
