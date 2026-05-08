package net.YTeron.Particls;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;

public class ExempleParticls extends TextureSheetParticle {
    private final float initialSize;

    protected ExempleParticls(ClientLevel pLevel, double pX, double pY, double pZ,
                              SpriteSet spriteSet,double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
        this.xd = pXSpeed + (random.nextDouble() - 0.5) * 0.1; //уменьшен разброс
        this.yd = pYSpeed + (random.nextDouble() - 0.5) * 0.1;
        this.zd = pZSpeed + (random.nextDouble() - 0.5) * 0.1;
        this.x = pX;
        this.y = pY;
        this.z = pZ;

        this.friction = 0.35f;
        this.lifetime = 15;
        this.alpha = 0.8f;
        this.setSpriteFromAge(spriteSet);

        this.rCol=1f;
        this.gCol=1f;
        this.bCol=1f;
        //this.quadSize = 0.05f;
        this.alpha = 0.2f;
        this.initialSize = 0.15f;

    }
    @Override
    public void tick() {
        super.tick();

        // Плавное затухание прозрачности
        this.alpha = 0.8f * (1.0f - (float)this.age / (float)this.lifetime);

        this.quadSize = initialSize  * (1.0f - (float)this.age / (float)this.lifetime);
    }
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel,
                                       double pX, double pY, double pZ,
                                       double pXSpeed, double pYSpeed, double pZSpeed) {
            return new ExempleParticls(pLevel, pX, pY, pZ, this.spriteSet, pXSpeed, pYSpeed, pZSpeed);
        }
    }
}