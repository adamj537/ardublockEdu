package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class VariableDigitalBlock extends TranslatorBlock
{
	public VariableDigitalBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode()
	{
		// If the string's variable is new...
		if (!translator.doesVariableExist(label))
		{
			// Remember it.
			translator.addVariable(label);
			
			// Create a C define statement for it.
			translator.addDefinitionCommand("bool " + label + ";");
		}
		
		// Form the C code.
		String ret = label;
		return codePrefix + ret + codeSuffix;
	}
}
