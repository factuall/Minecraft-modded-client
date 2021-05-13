package factuall.fiber.options;

public class IntOption extends Option{
	
	public int value;
	
	public IntOption(int value, String optionName, String optionDescription) {
		super(optionName, optionDescription);

		this.value = value;
	}
	
}
