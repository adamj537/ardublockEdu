package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class string_equalsIgnoreCase extends TranslatorBlock
{
	public string_equalsIgnoreCase(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode()  throws SocketNullException, SubroutineNotDeclaredException
	{
		String first;	// first string to compare
		String second;	// second string to compare
		
		// Fetch the first string.
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		first = translatorBlock.toCode();
		
		// If the first string is new...
		if (!translator.doesVariableExist(label))
		{
			// Remember it.
			translator.addVariable(label);
			
			// Create a C define statement for it.
			translator.addDefinitionCommand("String " + label + " = " + first + ";");
		}
		
		// Fetch the second string.
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		second = translatorBlock.toCode();

		// Form the C code.
		String ret = label + ".equalsIgnoreCase(" + second + ")";
		return codePrefix + ret + codeSuffix;
	}
}
