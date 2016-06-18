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
		
		// TODO:  Prevent the variable from having a global initialization.
		//			I've sort of done this already, in VariableFakeBlock.java.
		// TODO:  Figure out how to do multiple arguments.
		
		// Start forming the function's header.
		String ret = "void " + subroutineName + "(";
		
		// If the argument is an integer...
		if ((translatorBlock instanceof VariableFakeBlock) ||
			(translatorBlock instanceof VariableNumberBlock))
		{
			ret += "int " + var;
		}
		// If the argument is a double...
		else if (translatorBlock instanceof VariableNumberDoubleBlock)
		{
			ret += "double " + var;
		}
		// If the argument is a large integer...
		else if (translatorBlock instanceof VariableNumberUnsignedLongBlock)
		{
			ret += "unsigned long " + var;
		}
		
		// Finish the function's header.
		ret += ")\n{\n";
		
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
