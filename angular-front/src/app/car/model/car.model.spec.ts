import { Car } from './car.model';

describe('Car', () => {
  it('should create an instance', () => {
    // @ts-ignore
    expect(new Car()).toBeTruthy();
  });
});
