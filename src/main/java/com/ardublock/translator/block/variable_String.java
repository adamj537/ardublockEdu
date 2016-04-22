package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class variable_String extends TranslatorBlock
{
	public variable_String(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode()
	{
		// If the string is new...
		if (!translator.doesVariableExist(label))
		{
			// Remember it.
			translator.addVariable(label);
			
			// Create a C define statement for it.
			translator.addDefinitionCommand("String " + label + " = \"\";");
		}
		
		// Generate C code.
		return codePrefix + label + codeSuffix;
	}
}
