package com.ardublock.translator.block;

import java.util.ResourceBundle;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class RepeatControlBlock extends TranslatorBlock
{
	private static ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");
	
	public RepeatControlBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		// Fetch the variable name.
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		if (!(tb instanceof VariableNumberBlock || tb instanceof VariableNumberUnsignedLongBlock || tb instanceof VariableNumberDoubleBlock)) {
			throw new BlockException(blockId, uiMessageBundle.getString("ardublock.error_msg.number_var_slot"));
		}

		String varName = tb.toCode();
		
		// Fetch the starting value.
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		String startVal = tb.toCode();

		// Fetch the ending value.
		tb = this.getRequiredTranslatorBlockAtSocket(2);
		String stopVal = tb.toCode();

		// Fetch the increment value.
		tb = this.getRequiredTranslatorBlockAtSocket(3);
		String incVal = tb.toCode();

		// This string will be the code for the for loop.
		String ret = "";

		// If the loop is incrementing a variable...
		if (Integer.parseInt(startVal) <= Integer.parseInt(stopVal))
		{
			// If we're incrementing by one...
			if (Integer.parseInt(incVal) == 1)
			{
				// Form a string of the form:
				// for (x = START; x <= STOP; x++) {
				ret = ret + "for (" + varName + " = " + startVal + "; " + 
					varName + " <= " + stopVal + "; " + 
					varName + "++)\n{";
			}
			// If we're decrementing by more than one...
			else
			{
				// Form a string of the form:
				// for (x = START; x <= STOP; x += INCREMENT) {
				ret = ret + "for (" + varName + " = " + startVal + "; " + 
					varName + " <= " + stopVal + "; " + 
					varName + " += " + incVal + ")\n{";
			}
		}
		// If the loop is decrementing a variable...
		else
		{
			// If we're decrementing by one...
			if (Integer.parseInt(incVal) == 1)
			{
				// Form a string of the form:
				// for (x = START; x >= STOP; x--) {
				ret = ret + "for (" + varName + " = " + startVal + "; " + 
					varName + " >= " + stopVal + "; " + 
					varName + "--)\n{";
			}
			// If we're decrementing by more than one...
			else
			{
				// Form a string of the form:
				// for (x = START; x >= STOP; x -= INCREMENT) {
				ret = ret + "for (" + varName + " = " + startVal + "; " + 
					varName + " >= " + stopVal + "; " + 
					varName + " -= " + incVal + ")\n{";
			}
		}

		TranslatorBlock translatorBlock = getTranslatorBlockAtSocket(4);
		while (translatorBlock != null)
		{
			ret = ret + translatorBlock.toCode();
			translatorBlock = translatorBlock.nextTranslatorBlock();
		}
		
		ret = ret + "}\n";
		return ret;
	}
}
