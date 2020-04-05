package Questions;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.System.out;


class PrimeNumberTask extends CountedCompleter<Set<Integer>> {

	private final int MAX_TASK_CAPACITY = 4;
	private final int min;
	private final int offset;
	private final AtomicReference<Set<Integer>> result;

	public PrimeNumberTask(int min, int offset) {
		result = new AtomicReference<>();
		result.set(new ConcurrentSkipListSet<>());
		this.min = min;
		this.offset = offset;
	}

	public PrimeNumberTask(CountedCompleter<?> completer, int min, int offset, AtomicReference<Set<Integer>> result) {
		super(completer);
		this.min = min;
		this.offset = offset;
		this.result = result;
	}

	@Override
	public void compute() {
		if (offset > MAX_TASK_CAPACITY) {
			int midOffset = offset / MAX_TASK_CAPACITY;
			addToPendingCount(2);
			new PrimeNumberTask(this, min, midOffset, result).fork();
			new PrimeNumberTask(this, min + midOffset, offset - midOffset, result).fork();
		} else {
			out.println("Start computing in thread " + Thread.currentThread().getName());
			for (int i = min; i < min + offset; i++) {
				// ToDo: need to implement computing
				result.get().add(i);
			}
		}
		tryComplete();
	}

	@Override
	public Set<Integer> getRawResult() {
		return result.get();
	}
}

public class CountedCompleter_1 {
	public static void main(String[] args) {
		PrimeNumberTask primeNumberTask = new PrimeNumberTask(1, 128);
		Set<Integer> result = primeNumberTask.invoke();

		out.println(result.size());
		out.println(result);
	}
}