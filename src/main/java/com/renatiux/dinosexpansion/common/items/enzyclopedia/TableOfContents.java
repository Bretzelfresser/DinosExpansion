package com.renatiux.dinosexpansion.common.items.enzyclopedia;

import com.google.common.collect.Lists;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Dinosaur;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableOfContents {

    private Map<String, List<ITextComponent>> enumeration = new HashMap<>();


    public List<DoublePage> getRequiredPages(){
        System.out.println(enumeration.values());
        List<DoublePage> pages = Lists.newArrayList();
        for (String s : enumeration.keySet()) {
            EnumerationPage.Builder builder = EnumerationPage.Builder.create().setTitle(new TranslationTextComponent(s));
            for (ITextComponent text : enumeration.get(s)){
                if (!builder.isFull())
                    builder.addEnumeration(text);
                else {
                    add(builder.build(), pages);
                    builder = EnumerationPage.Builder.create();
                }
            }
            add(builder.build(), pages);
        }
        return pages;
    }

    /**
     * only usew for dummying that pages threw to know how much spaw the Table will need, dont use it when u want a functioning Table of contents
     */
    public void addPage(Page p){
        if (p instanceof DoublePage){
            addPage(((DoublePage)p).getLeftPage());
            addPage(((DoublePage)p).getRightPage());
        }
        if (p instanceof EntityPage){
            Entity e = ((EntityPage)p).getEntity();
            if (e instanceof Dinosaur){
                if (enumeration.containsKey("dinosaurs")){
                    enumeration.get("dinosaurs").add(p.title);
                }else{
                    enumeration.put("dinosaurs", Lists.newArrayList(p.title));
                }
            }else{
                if (enumeration.containsKey("environment")){
                    enumeration.get("environment").add(p.title);
                }else{
                    enumeration.put("environment", Lists.newArrayList(p.title));
                }
            }
        }else{
            if (enumeration.containsKey("misc")){
                enumeration.get("misc").add(p.title);
            }else{
                enumeration.put("misc", Lists.newArrayList(p.title));
            }
        }
    }

    public void addPage(Page p, int index){
        if (p instanceof DoublePage){
            addPage(((DoublePage)p).getLeftPage(), index * 2 - 1);
            addPage(((DoublePage)p).getRightPage(), index * 2);
        }
        if (p instanceof EntityPage){
            Entity e = ((EntityPage)p).getEntity();
            if (e instanceof Dinosaur){
                if (enumeration.containsKey("dinosaurs")){
                    enumeration.get("dinosaurs").add(withClickEvent(p.title, index));
                }else{
                    enumeration.put("dinosaurs", Lists.newArrayList(withClickEvent(p.title, index)));
                }
            }else{
                if (enumeration.containsKey("environment")){
                    enumeration.get("environment").add(withClickEvent(p.title, index));
                }else{
                    enumeration.put("environment", Lists.newArrayList(withClickEvent(p.title, index)));
                }
            }
        }else{
            if (enumeration.containsKey("misc")){
                enumeration.get("misc").add(withClickEvent(p.title, index));
            }else{
                enumeration.put("misc", Lists.newArrayList(withClickEvent(p.title, index)));
            }
        }
    }

    private ITextComponent withClickEvent(ITextComponent text, int index){
        return text.copyRaw().mergeStyle(text.copyRaw().getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, index + "")));
    }


    public static final void add(EnumerationPage page, List<DoublePage> pages){
        if (pages.size() == 0){
            pages.add(new DoublePage(page, Pages.BLANK_FILLER));
        }else{
            DoublePage lastPage = pages.get(pages.size() - 1);
            if (lastPage.getRightPage() == Pages.BLANK_FILLER){
                lastPage.setRight(page);
            }else{
                pages.add(new DoublePage(page, Pages.BLANK_FILLER));
            }
        }
    }


}
