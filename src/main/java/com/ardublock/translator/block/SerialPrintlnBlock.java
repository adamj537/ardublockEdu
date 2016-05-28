package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class SerialPrintlnBlock extends TranslatorBlock
{
	public SerialPrintlnBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		// Add code to set up the serial port.
		translator.addSetupCommand("Serial.begin(9600);");
		
		// Print the stuff.
		// This will add a separate line of code for each block that is "glued"
		// together.  So we use Serial.print() here and add a single newline
		// character afterwards.
		TranslatorBlock translatorBlock = this.getTranslatorBlockAtSocket(0, "Serial.print(", ");\n");
		
		// Form the string of code.
		String ret = "";
		if (translatorBlock != null)
		{
			ret = translatorBlock.toCode();
		}
		
		// Now print a newline character.
		ret += "Serial.println();\n";
		
		return ret;
	}
}
