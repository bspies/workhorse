# DotBPM Language

## Elements

### Core
Symbol | Type
-------|---------
`[]`   | Activity
`{}`   | Group
`#`    | Link

### Flows
Symbol | Type
-------|---------
`=>`   | Sequence
`->`   | Message
`--`   | Association

### Events

Symbol | Event
-------|------
`(>)`  | Start 
`(-)`  | End
`(X)`  | Cancel
`(<<)` | Compensation
`(~)`  | Error
`(@)`  | Message
`(!)`  | Signal
`(..)` | Timer
`(?)`  | Condition
`(^)`  | Escalation
`(*)`  | Multiple
`(+)`  | Multiple Parallel

### Gateways

Symbol | Gateway
-------|--------
`<X>`  | Exclusive (XOR)
`<O>`  | Inclusive (OR)
`<+>`  | Parallel (AND)
`<*>`  | Complex
`<()>` | Event-Based