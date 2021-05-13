package factuall.fiber.options;

public class StringOption extends Option{

	public String value;
	
	public StringOption(String value, String optionName, String optionDescription) {
		super(optionName, optionDescription);

		this.value = value;
	}
	
}
