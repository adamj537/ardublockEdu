package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class SetupBlock extends TranslatorBlock
{
	public SetupBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		// Create an empty string.
		String ret = "";
		
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
		
		// Add the string to the list of setup commands.
        translator.addSetupCommand(ret);
		
		// Return an empty string (because the setup() function is really
		// generated at a separate time).
		return "";
	}
}
