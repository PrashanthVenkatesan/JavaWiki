package com.java.util.ds;

import java.util.BitSet;

public class SimpleBitSet {

	public static void main(String[] args) {
		BitSet bs = new BitSet();
		BitSet bs1 = new BitSet(2);

		bs.set(0);
		bs.set(4);

		bs1.set(0);
		bs1.set(2);

		System.out.println(bs.toString() + " --- " + bs1.toString());

		BitSet bs2 = BitSet.valueOf(new long[] { Long.parseLong("101001", 2) });
		System.out.println(bs2.toString());

		System.out.println(bs2.cardinality()); // number of 1's in binary form
		// bs.get(bitIndex); -- Get Bit at given index
		// bs.clear(bitIndex); --reset bit at given index
		// bs.or(bs1); -- Bitwise OR
		// bs.and(bs1); -- Bitwise AND
		// bs.xor(bs1); -- XOR
		// bs.andNot(bs1); -- Bitwise AND then NOT
		// bs.clear(0, 2); -- clears bit 0 & 1
		// bs.flip(0); -- toggle bit at given index
		// bs.flip(0, 2); -- toggle bits 0 & 1
		System.out.println(bs.intersects(bs1));
		System.out.println(bs.nextClearBit(0));
		System.out.println(bs.nextSetBit(0));

		// bs.previousClearBit(1);
		// bs.previousSetBit(1);
		// bs.stream(); -- integer streaming bit from 0 to len - 1
		// bs.toByteArray();
		// bs.toLongArray();
	}

}
