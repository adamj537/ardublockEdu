package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class SubroutineBlock_var extends TranslatorBlock
{
	public SubroutineBlock_var(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		// Form a string with the function's name.
		String subroutineName = label.trim();
		
		// Fetch the function's argument block.
		TranslatorBlock translatorBlock = getRequiredTranslatorBlockAtSocket(0);
		
		// Translate it to a string.
		String var = translatorBlock.toCode();
		
		// Form the function's header.
		String ret = "void " + subroutineName + "(int " + var + ")\n{\n";
		
		// Fetch the function's body.
		translatorBlock = this.getTranslatorBlockAtSocket(1);
		
		// While there's an unprocessed block in the function's body...
		while (translatorBlock != null)
		{
			// Append the next block's code.
			ret = ret + translatorBlock.toCode();
			
			// Fetch the next block.
			translatorBlock = translatorBlock.nextTranslatorBlock();
		}
		
		// Add closing bracket.
		ret = ret + "}\n\n";
		
		// Return the resulting code.
		return ret;
	}
}
