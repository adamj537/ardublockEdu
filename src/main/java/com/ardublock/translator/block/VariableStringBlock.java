package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class VariableStringBlock extends TranslatorBlock
{
	public VariableStringBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		// If the string's variable is new...
		if (!translator.doesVariableExist(label))
		{
			// Remember it.
			translator.addVariable(label);
			
			// Create a C define statement for it.
			// TODO:  Add a way to set the size of the string.
			translator.addDefinitionCommand("char " + label + "[64] = \"\";");
		}
		
		// Form the C code.
		return codePrefix + label + codeSuffix;
	}
}
