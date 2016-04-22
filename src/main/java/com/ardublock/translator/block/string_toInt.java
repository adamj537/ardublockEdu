package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class string_toInt extends TranslatorBlock
{
	public string_toInt(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode()  throws SocketNullException, SubroutineNotDeclaredException
	{
		String first;	// string to compare
		String number;	// number to compare string with
		
		// Fetch the string.
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		first = translatorBlock.toCode();
		
		// If the string's variable is new...
		if (!translator.doesVariableExist(label))
		{
			// Remember it.
			translator.addVariable(label);
			
			// Create a C define statement for it.
			translator.addDefinitionCommand("String " + label + " = " + first + ";");
		}
		
		// Fetch the number.
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		number = translatorBlock.toCode();
		
		// Form the C code.
		String ret = label + ".toInt() == " + number;
		return codePrefix + ret + codeSuffix;
	}
}
