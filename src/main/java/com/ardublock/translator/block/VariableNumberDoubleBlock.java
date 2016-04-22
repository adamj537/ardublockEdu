package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class VariableNumberDoubleBlock extends TranslatorBlock
{
	public VariableNumberDoubleBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode()
	{
		// If the variable is new...
		if (!translator.doesVariableExist(label))
		{
			// Remember it.
			translator.addVariable(label);
			
			// Create a C define statement for it.
			translator.addDefinitionCommand("double " + label + " = 0.0 ;");
		}

		// Form the C code.
		return codePrefix + label + codeSuffix;
	}
}