package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

// TODO:  Rename to something generic, since it may be used by multiple block types?
public class VariableFakeBlock extends TranslatorBlock
{
	public VariableFakeBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode()
	{
		return codePrefix + label + codeSuffix;
	}
}
