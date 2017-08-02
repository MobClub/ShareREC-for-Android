using System;
using System.Globalization;


	public class HexByteConvert
	{
		public static string ByteArrayToHexString(sbyte[] byteArray)
		{
			return BitConverter.ToString(convert(byteArray)).Replace("-", ""); //To convert the whole array
		}

		public static sbyte[] HexStringToByteArray(string hexString)
		{

			byte[] HexAsBytes = new byte[hexString.Length / 2];
			for (int index = 0; index < HexAsBytes.Length; index++)
			{
				string byteValue = hexString.Substring(index * 2, 2);
				HexAsBytes[index] = byte.Parse(byteValue, NumberStyles.HexNumber, CultureInfo.InvariantCulture);
			}

			return convert(HexAsBytes);
		}


		private static sbyte[] convert(byte[] byteArray)
		{
			sbyte[] sbyteArray = new sbyte[byteArray.Length];
			for (int i = 0; i < sbyteArray.Length; i++)
			{
				sbyteArray[i] = unchecked((sbyte) byteArray[i]);
			}

			return sbyteArray;
		}

		public static byte[] convert(sbyte[] sbyteArray)
		{
			byte[] byteArray = new byte[sbyteArray.Length];
			for (int i = 0; i < byteArray.Length; i++)
			{
				byteArray[i] = (byte) sbyteArray[i];
			}
			return byteArray;
		}
	}


