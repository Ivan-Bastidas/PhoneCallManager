# Phone Call Manager Coding Sample

Implementation and tests for the PhoneCallManager class

The REST API:

- POST http://{host}/phonecall/phoneCallHappened

Description: Record the fact that a phone call happened between 2 numbers

example request:

```sh
 {
 "from":"+1 1234567890",
 "to":"+12 123456789"
 }
```

- GET http://{host}/phonecall/didPhoneCallHappen

Params:
    from: number that made the call
    to: number that was called

Description: Return whether a phone call has happened between the 2 numbers


example request:

```sh
 http://{host}/phonecall/didPhoneCallHappen?from=%2B1 1234567890&to=%2B12 123456789
```

- GET http://{host}/phonecall/didPhoneCallHappenAnyOrder

Params:
    numberA: First number involved in the call
    numberB: Second number involved in the call

Description: Return whether a phone call has happened between the 2 numbers in any order between the numbers


example request:

```sh
 http://{host}/phonecall/didPhoneCallHappenAnyOrder?numberA=%2B12 123456789&numberB=%2B1 1234567890
```
