package thatmartinguy.thedarkness.util;

import net.minecraft.util.text.TextFormatting;

public class ColorPulseHelper
{
    public static String pulsingString(String input, TextFormatting... formats)
    {
        int inputLength = input.length();
        if(inputLength < 0) return "";

        String output = "";

        for(int i = 0; i < formats.length; i++)
        {
            output = output+formats[i%formats.length]+input.substring(i, i + 1);
        }

        return TextFormatting.WHITE + output;
    }
}
