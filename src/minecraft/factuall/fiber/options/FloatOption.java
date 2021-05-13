package factuall.fiber.options;

public class FloatOption extends Option{

	public float value;
	
	public FloatOption(float value, String optionName, String optionDescription) {
		super(optionName, optionDescription);

		this.value = value;
	}
	
}
