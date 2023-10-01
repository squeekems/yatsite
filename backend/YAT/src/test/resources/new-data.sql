INSERT INTO prototype_events (prototype_event_id, ds_prompt, is_card, prompt) VALUES (1, NULL, FALSE, 'Roll a D20 to attempt. If the attempt roll is more than 10, you get away with something from his cart. Draw a card from the Greater Loot pile. If the attempt roll is no greater than 10, the merchant lands a decisive blow to your head. Roll a Saving Throw.');
INSERT INTO prototype_events (prototype_event_id, ds_prompt, is_card, prompt) VALUES (2, NULL, FALSE, 'If you have a card with the word "Coin" in the title, you may discard it at this time to draw a card from the Greater Loot pile. If you do not have a card with the word "Coin" in the title, the merchant says, "You have no coin! Get out of here!"');
INSERT INTO prototype_events (prototype_event_id, ds_prompt, is_card, prompt) VALUES (3, NULL, FALSE, 'The merchant takes pity on you. Draw a card from the Lesser Loot pile.');
INSERT INTO prototype_events (prototype_event_id, ds_prompt, is_card, prompt) VALUES (4, NULL, FALSE, 'Move your Progress Tracker up by 2.');
INSERT INTO prototype_events (prototype_event_id, ds_prompt, is_card, prompt) VALUES (5, NULL, TRUE, 'You run into a merchant. The merchant says, "I have wares to sell, if you have coin?"');

INSERT INTO options (option_id, label, result_id) VALUES (1, 'I attempt to rob the merchant.', 1);
INSERT INTO options (option_id, label, result_id) VALUES (2, 'I offer my coin.', 2);
INSERT INTO options (option_id, label, result_id) VALUES (3, 'What can I get for no coin?', 3);
INSERT INTO options (option_id, label, result_id) VALUES (4, 'I ignore them.', 4);

INSERT INTO prototype_event_options (prototype_event_id, option_id) VALUES (5, 1);
INSERT INTO prototype_event_options (prototype_event_id, option_id) VALUES (5, 2);
INSERT INTO prototype_event_options (prototype_event_id, option_id) VALUES (5, 3);
INSERT INTO prototype_event_options (prototype_event_id, option_id) VALUES (5, 4);

INSERT INTO sentences (sentence, flag) VALUES ('You are there, in the room of your choice, relaxing.', 'intro');

INSERT INTO items(item_id, name, damage, type, effects, is_greater, count) VALUES (1, 'Battleaxe', 6, 'Weapon - Blade of War', '', TRUE, 2);