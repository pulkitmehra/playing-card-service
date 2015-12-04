/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.web.adapter;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.nike.cards.core.ResourceAlreadyExistsException;
import com.nike.cards.core.ResourceNotFoundException;
import com.nike.cards.core.model.Deck;
import com.nike.cards.core.service.DeckService;
import com.nike.cards.core.service.DeckServiceException;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Spring web adapter implementing {@link DeckWebAdapter}.
 *
 * @author pulkit.mehra
 * Created: Dec 4, 2015
 */
@Controller
@RequestMapping("/deck")
public class SpringDeckWebAdapter implements DeckWebAdapter {

    /** The deck service. */
    private DeckService deckService;

    /**
     * Instantiates a new spring deck web adapter.
     *
     * @param deckService the deck service
     */
    @Autowired
    public SpringDeckWebAdapter(DeckService deckService) {
        this.deckService = deckService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(method = GET, value = "/deckname/{deckName}", produces = "application/json")
    public @ResponseBody Deck getDeck(@PathVariable("deckName") String deckName)
        throws DeckServiceException, ResourceNotFoundException {
        return deckService.getDeck(deckName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(method = GET, value = "/all", produces = "application/json")
    public @ResponseBody DeckNamesWrapper getAllDecks() throws DeckServiceException {
        DeckNamesWrapper wrapper = new DeckNamesWrapper();
        Collection<Deck> allDecks = deckService.getAllDecks();
        for (Deck deck : allDecks) {
            wrapper.getDecks().add(deck.getDeckName());
        }
        return wrapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(method = PUT, value = "/deckname/{deckName}", produces = "application/json")
    public @ResponseBody Deck createDeck(@PathVariable("deckName") String deckName)
        throws DeckServiceException, ResourceAlreadyExistsException {
        return deckService.createDeck(deckName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(method = DELETE, value = "/deckname/{deckName}", produces = "application/json")
    @ResponseStatus(value = OK)
    public void removeDeck(@PathVariable("deckName") String deckName)
        throws DeckServiceException, ResourceNotFoundException {
        deckService.remove(deckName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(method = POST, value = "/shuffle", produces = "application/json")
    public @ResponseBody Deck shuffle(@RequestParam("deckname") String deckName)
        throws DeckServiceException, ResourceNotFoundException {
        return deckService.shuffleDeck(deckName);
    }

}
