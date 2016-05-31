package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class LoopBlock extends TranslatorBlock
{
	public LoopBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		// Create a string with the first part of the loop() function.
		String ret = "void loop()\n{\n";
		
		// Fetch the first block to translate.
		TranslatorBlock translatorBlock = getTranslatorBlockAtSocket(0);
		
		// For each block found...
		while (translatorBlock != null)
		{
			// Translate the block to code and add it to the string.
			ret += translatorBlock.toCode();
			
			// Fetch the next block to translate.
			translatorBlock = translatorBlock.nextTranslatorBlock();
			
			// If there's another block to translate...
			if (translatorBlock != null)
			{
				// Add a newline in between.
				ret += "\n";
			}
		}
		
		// Add the ending bracket, and two newlines.
		ret += "}\n\n";
		
		// Return the generated string of code.
		return ret;
	}
}
