package com.java.util.ds;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class SimpleDoubleSummaryStatistics {
	// Similarly IntSummaryStatistics, LongSummaryStatistics also there
	public static void main(String[] args) {
		List<Student> l = new ArrayList<>();
		l.add(new Student(100.0));
		l.add(new Student(75.0));
		l.add(new Student(89.0));
		l.add(new Student(92.0));
		l.add(new Student(70.0));
		l.add(new Student(81.0));

		DoubleSummaryStatistics stats1 = doublesummarystatisticswithstream(l);
		DoubleSummaryStatistics stats2 = doublesummarystatisticswithreduction(l);

		stats1.combine(stats2);
		printStats(stats1);

		DoubleSummaryStatistics stats3 = DoubleStream.of(5.33d, 2.34d, 5.32d, 2.31d, 3.51d).collect(
				DoubleSummaryStatistics::new, DoubleSummaryStatistics::accept, DoubleSummaryStatistics::combine);
		printStats(stats3);

		final DoubleSummaryStatistics stats4 = new DoubleSummaryStatistics();
		stats4.accept(5.0);
		stats4.accept(10.0);
		stats4.accept(15.0);
		stats4.accept(20.0);
		printStats(stats4);
		
		/*stats1.andThen(new DoubleConsumer() {
			@Override
			public void accept(double value) {
			}
		});*/

	}

	private static DoubleSummaryStatistics doublesummarystatisticswithreduction(List<Student> l) {
		DoubleSummaryStatistics stats = l.stream().collect(Collectors.summarizingDouble(Student::getMarks));
		printStats(stats);
		return stats;
	}

	private static DoubleSummaryStatistics doublesummarystatisticswithstream(List<Student> l) {
		DoubleSummaryStatistics stats = l.stream().mapToDouble((x) -> x.getMarks()).summaryStatistics();
		printStats(stats);
		return stats;
	}

	private static void printStats(DoubleSummaryStatistics stats) {
		System.out.println("Result-----------------");
		System.out.println("Sum -- " + stats.getSum());
		System.out.println("Count -- " + stats.getCount());
		System.out.println("Min -- " + stats.getMin());
		System.out.println("Max -- " + stats.getMax());
		System.out.println("Average -- " + stats.getAverage());
	}
}

class Student {
	private double marks;

	public double getMarks() {
		return marks;
	}

	public Student(double marks) {
		this.marks = marks;
	}
}