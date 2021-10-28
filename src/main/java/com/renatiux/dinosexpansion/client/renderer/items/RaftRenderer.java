package com.renatiux.dinosexpansion.client.renderer.items;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.model.entities.items.RaftModelChested;
import com.renatiux.dinosexpansion.client.model.entities.items.RaftModelNotChested;
import com.renatiux.dinosexpansion.common.entities.RaftEntity;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class RaftRenderer extends EntityRenderer<RaftEntity>{

	RaftModelChested withChest = new RaftModelChested();
	RaftModelNotChested withoutChest = new RaftModelNotChested();
	
	public RaftRenderer(EntityRendererManager renderManager) {
		super(renderManager);
		this.shadowSize = 1.2F;
	}
	
	@Override
	 public void render(RaftEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
	      matrixStackIn.push();
	      matrixStackIn.translate(0.0D, -0.375D, 0.0D);
	      matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
	      float f = (float)entityIn.getTimeSinceHit() - partialTicks;
	      float f1 = entityIn.getDamageTaken() - partialTicks;
	      if (f1 < 0.0F) {
	         f1 = 0.0F;
	      }

	      if (f > 0.0F) {
	         matrixStackIn.rotate(Vector3f.XP.rotationDegrees(MathHelper.sin(f) * f * f1 / 10.0F * (float)entityIn.getForwardDirection()));
	      }

	      float f2 = entityIn.getRockingAngle(partialTicks);
	      if (!MathHelper.epsilonEquals(f2, 0.0F)) {
	         matrixStackIn.rotate(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), entityIn.getRockingAngle(partialTicks), true));
	      }

	      matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
	      matrixStackIn.translate(0.0D, -2.1D,0.0D);
	      matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
	      if(entityIn.hasChest()) {
	      this.withChest.setRotationAngles(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
	      IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.withChest.getRenderType(this.getEntityTexture(entityIn)));
	      this.withChest.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	      }else {
	    	  this.withoutChest.setRotationAngles(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
		      IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.withoutChest.getRenderType(this.getEntityTexture(entityIn)));
		      this.withoutChest.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	      }

	      matrixStackIn.pop();
	      super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	   }

	@Override
	public ResourceLocation getEntityTexture(RaftEntity entity) {
		return Dinosexpansion.modLoc("textures/entity/boats/raft.png");
	}


}