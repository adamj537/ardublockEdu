package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class DigitalOutputBlock extends TranslatorBlock
{
	public DigitalOutputBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		// Fetch the pin number argument.
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		
		// If the pin is a constant (a pin we're always using as an output)...
		if (translatorBlock instanceof NumberBlock)
		{
			String number = translatorBlock.toCode();
			String setupCode = "pinMode(" + number + ", OUTPUT);";
			translator.addSetupCommand(setupCode);
			
			String ret = "digitalWrite(";
			ret = ret + number;
			ret = ret + ", ";
			translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
			ret = ret + translatorBlock.toCode();
			ret = ret + ");\n";
			return ret;
		}
		// If the pin is a variable (i.e. it might not always be an output)...
		else
		{
			// Fetch the pin's name.
			String pin = translatorBlock.toCode();
			
			// Set the pin direction to output.
			String ret = "pinMode(" + pin + ", OUTPUT);\n";
			
			// Fetch the desired output level.
			translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
			String status = translatorBlock.toCode();
			
			// Set the pin's output level.
			ret += "digitalWrite(" + pin + ", " + status + ");\n";
			
			return ret;
		}
	}
}
