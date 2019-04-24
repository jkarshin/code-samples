package com.jake.tester;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

// TODO Placeholder test, delete this
public class TempTest {
	@Test
	void tempTest() throws Exception {
		assertTrue(true);
		assertEquals(4, 4);
		assertThrows(IllegalArgumentException.class, () -> {
			throw new IllegalArgumentException();
		});
	}
}
