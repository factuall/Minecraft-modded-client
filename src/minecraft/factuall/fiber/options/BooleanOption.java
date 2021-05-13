package factuall.fiber.options;

public class BooleanOption extends Option{

	public Boolean value;
	
	public BooleanOption(Boolean value, String optionName, String optionDescription) {
		super(optionName, optionDescription);

		this.value = value;
	}
	
}
