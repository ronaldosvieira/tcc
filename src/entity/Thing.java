package entity;

import frames.GenericFrame;
import frames.InstanceFrame;
import frames.KnowledgeBase;
import org.joml.Vector2f;
import world.World;

public abstract class Thing extends Entity {
	private String name;
	protected InstanceFrame semantic;

	public Thing(String name, World world, int amountAnim, Vector2f scale, Vector2f position) {
		super(world, amountAnim, scale, position);
		
		this.name = name;
		this.semantic = new InstanceFrame(name,
				(GenericFrame) KnowledgeBase.retrieveFrame(name));
	}

	public String getName() {return this.name;}
	public InstanceFrame getSemantic() {return this.semantic;}

    @Override
    public void destroy() {
        super.destroy();

        this.semantic.set("exists", false);
    }
}
