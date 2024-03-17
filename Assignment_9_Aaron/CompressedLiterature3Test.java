import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class CompressedLiterature3Test
{
	private HuffmanEncoder huffman;
	@BeforeEach
	public final void setup() throws IOException {
		huffman = new HuffmanEncoder();
	}
	
	@Test
	public final void compressed_lit() throws Exception
	{
		assertEquals(true, huffman.book.book.length() == 3291642 || huffman.book.book.length() == 3291623, "compressed_lit failed");
		assertEquals(570240, huffman.book.words.size(), "compressed_lit failed");
	    assertEquals(true, huffman.frequenciesHash.size() == 20251 || huffman.frequenciesHash.size() == 20255, "compressed_lit failed");
		assertEquals(true, huffman.encodedText.length == 1035074 || huffman.encodedText.length == 1035040, "compressed_lit failed");
		long size = Files.size(Path.of("./WarAndPeace-compressed.bin"));
		assertEquals(true, size == 1035074 || size == 1035040, "compressed_lit failed");
	}
}


