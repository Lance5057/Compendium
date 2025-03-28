package com.lance5057.compendium.client.models;

import net.minecraft.client.resources.model.BakedModel;

//Basic implementation, only holds 1 material type
public class BasicIndexModel {
	public final String material;
	public final BakedModel model;

//	List<BakedQuad> up = new ArrayList<BakedQuad>();
//	List<BakedQuad> down = new ArrayList<BakedQuad>();
//	List<BakedQuad> north = new ArrayList<BakedQuad>();
//	List<BakedQuad> south = new ArrayList<BakedQuad>();
//	List<BakedQuad> west = new ArrayList<BakedQuad>();
//	List<BakedQuad> east = new ArrayList<BakedQuad>();
//	List<BakedQuad> none = new ArrayList<BakedQuad>();

//	public Map<Direction, List<BakedQuad>> quads = new HashMap<Direction, List<BakedQuad>>();

	public BasicIndexModel(String m, BakedModel bm) {
		this.material = m;
		this.model = bm;
	}

//	public void add(Direction d, BakedQuad q) {
//		if (d == null)
//			none.add(q);
//		else
//			switch (d) {
//			case Direction.UP:
//				up.add(q);
//				break;
//			case Direction.DOWN:
//				down.add(q);
//				break;
//			case Direction.NORTH:
//				north.add(q);
//				break;
//			case Direction.SOUTH:
//				south.add(q);
//				break;
//			case Direction.EAST:
//				east.add(q);
//				break;
//			case Direction.WEST:
//				west.add(q);
//				break;
//			default:
//				none.add(q);
//				break;
//			}
//	}
//
//	public void addAll(Direction d, List<BakedQuad> q) {
//		if (d == null)
//			none.addAll(q);
//		else
//			switch (d) {
//			case Direction.UP:
//				up.addAll(q);
//				break;
//			case Direction.DOWN:
//				down.addAll(q);
//				break;
//			case Direction.NORTH:
//				north.addAll(q);
//				break;
//			case Direction.SOUTH:
//				south.addAll(q);
//				break;
//			case Direction.EAST:
//				east.addAll(q);
//				break;
//			case Direction.WEST:
//				west.addAll(q);
//				break;
//			default:
//				none.addAll(q);
//				break;
//			}
//	}
//
//	public List<BakedQuad> get(Direction d) {
//		if (d == null)
//			return none;
//		else
//			switch (d) {
//			case Direction.UP:
//				return up;
//			case Direction.DOWN:
//				return down;
//			case Direction.NORTH:
//				return north;
//			case Direction.SOUTH:
//				return south;
//			case Direction.EAST:
//				return east;
//			case Direction.WEST:
//				return west;
//			default:
//				return none;
//			}
//	}

}
