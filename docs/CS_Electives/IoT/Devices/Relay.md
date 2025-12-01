# Relay

Electromagnetic switch

Working principle: Electromagnetic induction

![Relay Module – ROBO CY](./assets/RelayModule.png){ loading=lazy }

Be careful when working with relays, as they may short-circuit. Don’t touch the back side and don’t keep it near other conductors and electronics

## Pins

![image-20240222003224138](./assets/relay_module_pin_diagram.png){ loading=lazy }

![image-20240222004049737](./assets/relay_module_wiring_diagram.png){ loading=lazy }

- VCC: +5v
- GND
- IN: Input (D2)
- NC: Normally-closed/connected
- NO: Normally-open
- COM: Common

Internal connections

- NC & COM with spring
- NO & IN

| IN   | NO                    | NC - COM                              | NO - COM  |
| ---- | --------------------- | ------------------------------------- | --------- |
| On   | Behaves like a magnet | Break                                 | Attracted |
| Off  | No effect             | Revert<br />Makes a ‘tick-tick’ sound | Break     |

If there is no ‘tick-tick’ sound, then the relay isn’t getting sufficient voltage