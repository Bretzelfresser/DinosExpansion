package com.renatiux.dinosexpansion.client.model.backedModels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableList;
import com.renatiux.dinosexpansion.Dinosexpansion;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;

public class CableModel implements IDynamicBakedModel {

	public static final ResourceLocation TEXTURE = Dinosexpansion.modLoc("block/cable/cable_texture");

	private final VertexFormat format;

	public CableModel(VertexFormat format) {
		this.format = format;
	}

	private TextureAtlasSprite getTexture() {
		return Minecraft.getInstance().getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(TEXTURE);
	}

	private void putVertex(BakedQuadBuilder builder, Vector3d normal, double x, double y, double z, float u, float v, TextureAtlasSprite sprite, float r, float g, float b) {
        ImmutableList<VertexFormatElement> elements = builder.getVertexFormat().getElements().asList();
        for (int j = 0; j < elements.size(); j++) {
            VertexFormatElement e = elements.get(j);
            switch (e.getUsage()) {
                case POSITION:
                    builder.put(j, (float) x, (float) y, (float) z, 1.0f);
                    break;
                case COLOR:
                    builder.put(j, r, g, b, 1.0f);
                    break;
                case UV:
                    switch (e.getIndex()) {
                        case 0:
                            float iu = sprite.getInterpolatedU(u);
                            float iv = sprite.getInterpolatedU(v);
                            builder.put(j, iu, iv);
                            break;
                        case 2:
                            builder.put(j, (short) 0, (short) 0);
                            break;
                        default:
                            builder.put(j);
                            break;
                    }
                    break;
                case NORMAL:
                    builder.put(j, (float) normal.x, (float) normal.y, (float) normal.z);
                    break;
                default:
                    builder.put(j);
                    break;
            }
        }
    }

	/*
	private Collection<BakedQuad> createdQube(Vector3d from, Vector3d to, Map<Direction, Float[]> uv, TextureAtlasSprite texture){
		if(uv.size() != 6) {
			throw new IllegalArgumentException("map has to contain uv for every direction");
		}
		List<BakedQuad> quads = new ArrayList<>();
		//from plain z-wise with from vector
		Vector3d v1 = v(to.x, from.y, from.z);
		Vector3d v2 = v(to.x, to.y, from.z);
		Vector3d v3 = v(from.x, to.y, from.z);
		
		//to plain z-wise with to vector
		Vector3d v4 = v(to.x, from.y, to.z);
		//on a line with from
		Vector3d v5 = v(from.x, from.y, to.z);
		Vector3d v6 = v(from.x, to.y, to.z);
		
		quads.add(createQuad(v1, v2, v3, from, texture, copy(uv.get(Direction.NORTH))));
		quads.add(createQuad(v4, v5, v6, to, texture, copy(uv.get(Direction.SOUTH))));
		quads.add(createQuad(v1, v2, v4, to, texture, copy(uv.get(Direction.EAST))));
		quads.add(createQuad(v3, v5, v6, from, texture, copy(uv.get(Direction.WEST))));
		quads.add(createQuad(v1, v4, v5, from, texture, copy(uv.get(Direction.UP))));
		quads.add(createQuad(v2, v3, v6, to, texture, copy(uv.get(Direction.DOWN))));
		
		return quads;
		
		
	}*/
	
	 private BakedQuad createQuad(Vector3d v1, Vector3d v2, Vector3d v3, Vector3d v4, TextureAtlasSprite sprite) {
	        Vector3d normal = v3.subtract(v2).crossProduct(v1.subtract(v2)).normalize();
	        int tw = sprite.getWidth();
	        int th = sprite.getHeight();
	        BakedQuadBuilder builder = new BakedQuadBuilder(sprite);
	        builder.setQuadOrientation(Direction.getFacingFromVector(normal.x, normal.y, normal.z));
	        putVertex(builder, normal, v1.x, v1.y, v1.z, 0, 0, sprite, 1.0f, 1.0f, 1.0f);
	        putVertex(builder, normal, v2.x, v2.y, v2.z, 0, th, sprite, 1.0f, 1.0f, 1.0f);
	        putVertex(builder, normal, v3.x, v3.y, v3.z, tw, th, sprite, 1.0f, 1.0f, 1.0f);
	        putVertex(builder, normal, v4.x, v4.y, v4.z, tw, 0, sprite, 1.0f, 1.0f, 1.0f);
	        return builder.build();
	    }
	
	
	private Vector3d v(double x, double y, double z) { return new Vector3d(x, y, z); }
	/**
	 * utility not nullChecked
	 */
	private float[] copy(Float[] toCopy) {
		float[] ret = new float[toCopy.length];
		for(int i = 0;i<toCopy.length;i++) {
			ret[i] = toCopy[i];
		}
		
		return ret;
	}

	@Override
	public boolean isAmbientOcclusion() {
		return true;
	}

	@Override
	public boolean isGui3d() {
		return false;
	}

	@Override
	public boolean isSideLit() {
		return false;
	}

	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return getTexture();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return ItemOverrideList.EMPTY;
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand, IModelData extraData) {
		List<BakedQuad> quads = new ArrayList<>();
		quads.add(createQuad(v(.2f,1f - .2f,.2f), v(.2f,1f - .2f,1f - .2f), v(1f - .2f,1f - .2f,1f - .2f), v(1f - .2f,1f - .2f,.2f), getTexture()));
		quads.add(createQuad(v(.2f,.2f,.2f), v(1f - .2f,.2f,.2f), v(1f - .2f,.2f,1f - .2f), v(.2f,.2f,1f - .2f), getTexture()));
		quads.add(createQuad(v(1f - .2f,1f - .2f,1f - .2f), v(1f - .2f,.2f,1f - .2f), v(1f - .2f,.2f,.2f), v(1f - .2f,1f - .2f,.2f), getTexture()));
		quads.add(createQuad(v(.2f,1f - .2f,.2f), v(.2f,.2f,.2f), v(.2f,.2f,1f - .2f), v(.2f,1f - .2f,1f - .2f), getTexture()));
		quads.add(createQuad(v(1f - .2f,1f - .2f,.2f), v(1f - .2f,.2f,.2f), v(.2f,.2f,.2f), v(.2f,1f - .2f,.2f), getTexture()));
		quads.add(createQuad(v(.2f,1f - .2f,1f - .2f), v(.2f,.2f,1f - .2f), v(1f - .2f,.2f,1f - .2f), v(1f - .2f,1f - .2f,1f - .2f), getTexture()));
		/*
		Map<Direction, Float[]> faces = new HashMap<>();
		faces.put(Direction.NORTH, new Float[] {0.5f, 0.5f, 1f, 1f});
		faces.put(Direction.EAST, new Float[] {0f, 0.5f, 0.5f, 1f});
		faces.put(Direction.SOUTH, new Float[] {1.5f, 0.5f, 2f, 1f});
		faces.put(Direction.WEST, new Float[] {1f, 0.5f, 1.5f, 1f});
		faces.put(Direction.UP, new Float[] {1f, 0.5f, 0.5f, 0f});
		faces.put(Direction.DOWN, new Float[] {1.5f, 0f, 1f, 0.5f});
		quads.addAll(createdQube(v(6.7, 7, 6.7), v(9.2,9.4,9.2), faces, getParticleTexture()));*/
		
		return quads;
	}

}
