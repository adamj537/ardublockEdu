package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class VariablePolyBlock extends TranslatorBlock
{
	public VariablePolyBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
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
			
			// Create a define statement for it.
			translator.addDefinitionCommand("char " + label + " = \' \' ;");
		}
		
		// Generate code.
		return codePrefix + label + codeSuffix;
	}
}
