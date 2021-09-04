package com.epam.jwd.texthandling.service.sort;

import com.epam.jwd.texthandling.exception.WrongTextComponentException;
import com.epam.jwd.texthandling.model.TextComponent;
import com.epam.jwd.texthandling.model.TextComposite;
import com.epam.jwd.texthandling.model.TextPart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public interface SortService {

    Logger LOG = LogManager.getLogger(SortService.class);

    String WRONG_TEXT_COMPONENT_MSG = "%s can't be applied to %s entity";

    default TextComposite sort(TextComposite text, Comparator<TextComponent> comparator) {
        try {
            if (text.getParts().get(0).getType() == TextPart.PARAGRAPH) {
                return sortText(text, comparator);
            } else {
                generateException(text);
            }
        } catch (WrongTextComponentException e) {
            LOG.warn(e);
        }
        return text;
    }

    TextComposite sortText(TextComposite text, Comparator<TextComponent> comparator);

    default void generateException(TextComposite text) {
        String msg = String.format(WRONG_TEXT_COMPONENT_MSG,
                                   this.getClass().getSimpleName(),
                                   text.getParts().get(0).getType());
        throw new WrongTextComponentException(msg);
    }
}
